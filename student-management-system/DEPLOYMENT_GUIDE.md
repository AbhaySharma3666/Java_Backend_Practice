# Deployment Guide

## Table of Contents
1. [Local Deployment](#local-deployment)
2. [Docker Deployment](#docker-deployment)
3. [AWS Deployment](#aws-deployment)
4. [Heroku Deployment](#heroku-deployment)
5. [Production Checklist](#production-checklist)

---

## Local Deployment

### Prerequisites
- JDK 17
- Maven 3.6+
- MySQL 8.0+

### Steps

1. **Setup Database**
```bash
mysql -u root -p < database-setup.sql
```

2. **Configure Application**
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/college
spring.datasource.username=root
spring.datasource.password=your_password
```

3. **Build Application**
```bash
mvn clean package -DskipTests
```

4. **Run Application**
```bash
java -jar target/student-management-system-0.0.1-SNAPSHOT.jar
```

5. **Access Application**
```
http://localhost:8080
```

---

## Docker Deployment

### Using Docker Compose (Recommended)

1. **Build and Start**
```bash
docker-compose up -d
```

2. **View Logs**
```bash
docker-compose logs -f app
```

3. **Stop Services**
```bash
docker-compose down
```

### Using Docker Only

1. **Build Image**
```bash
docker build -t sms-app .
```

2. **Run MySQL Container**
```bash
docker run -d \
  --name sms-mysql \
  -e MYSQL_ROOT_PASSWORD=mysql \
  -e MYSQL_DATABASE=college \
  -p 3306:3306 \
  mysql:8.0
```

3. **Run Application Container**
```bash
docker run -d \
  --name sms-app \
  --link sms-mysql:mysql \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/college \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=mysql \
  -p 8080:8080 \
  sms-app
```

---

## AWS Deployment

### Option 1: AWS Elastic Beanstalk

1. **Install EB CLI**
```bash
pip install awsebcli
```

2. **Initialize EB**
```bash
eb init -p java-17 student-management-system
```

3. **Create Environment**
```bash
eb create sms-prod-env
```

4. **Configure RDS**
- Create RDS MySQL instance
- Update environment variables:
```bash
eb setenv SPRING_DATASOURCE_URL=jdbc:mysql://your-rds-endpoint:3306/college
eb setenv SPRING_DATASOURCE_USERNAME=admin
eb setenv SPRING_DATASOURCE_PASSWORD=your_password
```

5. **Deploy**
```bash
mvn clean package
eb deploy
```

### Option 2: AWS ECS (Fargate)

1. **Push Image to ECR**
```bash
aws ecr create-repository --repository-name sms-app
docker tag sms-app:latest <account-id>.dkr.ecr.<region>.amazonaws.com/sms-app:latest
docker push <account-id>.dkr.ecr.<region>.amazonaws.com/sms-app:latest
```

2. **Create Task Definition**
```json
{
  "family": "sms-task",
  "networkMode": "awsvpc",
  "requiresCompatibilities": ["FARGATE"],
  "cpu": "512",
  "memory": "1024",
  "containerDefinitions": [{
    "name": "sms-app",
    "image": "<account-id>.dkr.ecr.<region>.amazonaws.com/sms-app:latest",
    "portMappings": [{
      "containerPort": 8080,
      "protocol": "tcp"
    }],
    "environment": [
      {"name": "SPRING_DATASOURCE_URL", "value": "jdbc:mysql://..."},
      {"name": "SPRING_DATASOURCE_USERNAME", "value": "admin"},
      {"name": "SPRING_DATASOURCE_PASSWORD", "value": "password"}
    ]
  }]
}
```

3. **Create Service**
```bash
aws ecs create-service \
  --cluster sms-cluster \
  --service-name sms-service \
  --task-definition sms-task \
  --desired-count 2 \
  --launch-type FARGATE
```

### Option 3: AWS EC2

1. **Launch EC2 Instance**
- AMI: Amazon Linux 2
- Instance Type: t2.medium
- Security Group: Allow ports 22, 8080

2. **Connect and Setup**
```bash
ssh -i your-key.pem ec2-user@your-instance-ip

# Install Java
sudo yum install java-17-amazon-corretto -y

# Install MySQL
sudo yum install mysql -y

# Transfer JAR file
scp -i your-key.pem target/*.jar ec2-user@your-instance-ip:~/
```

3. **Run Application**
```bash
nohup java -jar student-management-system-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:mysql://your-rds-endpoint:3306/college \
  --spring.datasource.username=admin \
  --spring.datasource.password=password \
  > app.log 2>&1 &
```

4. **Setup as Service**
Create `/etc/systemd/system/sms.service`:
```ini
[Unit]
Description=Student Management System
After=syslog.target

[Service]
User=ec2-user
ExecStart=/usr/bin/java -jar /home/ec2-user/student-management-system-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
Environment="SPRING_DATASOURCE_URL=jdbc:mysql://..."
Environment="SPRING_DATASOURCE_USERNAME=admin"
Environment="SPRING_DATASOURCE_PASSWORD=password"

[Install]
WantedBy=multi-user.target
```

Enable and start:
```bash
sudo systemctl enable sms
sudo systemctl start sms
```

---

## Heroku Deployment

1. **Install Heroku CLI**
```bash
curl https://cli-assets.heroku.com/install.sh | sh
```

2. **Login to Heroku**
```bash
heroku login
```

3. **Create Application**
```bash
heroku create sms-college-app
```

4. **Add MySQL Addon**
```bash
heroku addons:create jawsdb:kitefin
```

5. **Configure Environment**
```bash
heroku config:set SPRING_PROFILES_ACTIVE=prod
```

6. **Create Procfile**
```
web: java -Dserver.port=$PORT -jar target/student-management-system-0.0.1-SNAPSHOT.jar
```

7. **Deploy**
```bash
git add .
git commit -m "Deploy to Heroku"
git push heroku main
```

8. **Open Application**
```bash
heroku open
```

---

## Production Checklist

### Security
- [ ] Change default passwords
- [ ] Enable HTTPS/SSL
- [ ] Configure CORS properly
- [ ] Enable CSRF protection for web forms
- [ ] Use environment variables for secrets
- [ ] Implement rate limiting
- [ ] Enable security headers
- [ ] Regular security audits

### Database
- [ ] Setup database backups
- [ ] Configure connection pooling
- [ ] Enable query logging
- [ ] Optimize indexes
- [ ] Setup read replicas (if needed)
- [ ] Configure automatic failover

### Monitoring
- [ ] Setup application monitoring (New Relic, DataDog)
- [ ] Configure log aggregation (ELK, CloudWatch)
- [ ] Setup alerts for errors
- [ ] Monitor resource usage
- [ ] Track API performance
- [ ] Setup uptime monitoring

### Performance
- [ ] Enable caching (Redis)
- [ ] Configure CDN for static assets
- [ ] Optimize database queries
- [ ] Enable compression
- [ ] Configure load balancing
- [ ] Setup auto-scaling

### Backup & Recovery
- [ ] Automated database backups
- [ ] Disaster recovery plan
- [ ] Test restore procedures
- [ ] Document recovery steps
- [ ] Setup backup monitoring

### Documentation
- [ ] API documentation
- [ ] Deployment procedures
- [ ] Troubleshooting guide
- [ ] Architecture diagrams
- [ ] Runbook for operations

### Testing
- [ ] Run all tests
- [ ] Performance testing
- [ ] Security testing
- [ ] Load testing
- [ ] Smoke tests in production

### Configuration
- [ ] Use production profile
- [ ] Configure proper logging levels
- [ ] Set appropriate timeouts
- [ ] Configure thread pools
- [ ] Setup health checks

---

## Environment Variables

### Required
```bash
SPRING_DATASOURCE_URL=jdbc:mysql://host:3306/college
SPRING_DATASOURCE_USERNAME=admin
SPRING_DATASOURCE_PASSWORD=secure_password
```

### Optional
```bash
SERVER_PORT=8080
SPRING_PROFILES_ACTIVE=prod
ADMIN_PASSWORD=secure_admin_password
LOG_LEVEL=INFO
```

---

## Troubleshooting

### Application Won't Start
- Check Java version: `java -version`
- Verify database connectivity
- Check port availability: `netstat -an | grep 8080`
- Review application logs

### Database Connection Issues
- Verify database is running
- Check credentials
- Verify network connectivity
- Check firewall rules

### Performance Issues
- Check database query performance
- Monitor memory usage
- Review thread pool configuration
- Check for memory leaks

---

## Rollback Procedures

### Docker
```bash
docker-compose down
docker-compose up -d --build
```

### AWS Elastic Beanstalk
```bash
eb deploy --version previous-version
```

### Manual Deployment
```bash
# Stop application
sudo systemctl stop sms

# Restore previous version
cp backup/previous-version.jar current.jar

# Start application
sudo systemctl start sms
```

---

## Support

For deployment issues:
- Check logs: `tail -f logs/application.log`
- Review documentation
- Contact support team

---

## Additional Resources

- [Spring Boot Production Best Practices](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html)
- [AWS Deployment Guide](https://aws.amazon.com/getting-started/)
- [Docker Documentation](https://docs.docker.com/)
- [Heroku Java Support](https://devcenter.heroku.com/categories/java-support)
