# Kubernetes Deployment Guide for Notification Service

This guide explains how to deploy, manage, and test the **Notification Service** on a Kubernetes cluster using Docker Desktop with Kubernetes enabled.

---

## Prerequisites

Before starting, ensure you have:

- Docker Desktop installed with **Kubernetes Cluster enabled**
- Built and pushed your Docker image to Docker Hub (e.g., `sambathreasmey/notification:1.0`)
- `kubectl` installed and configured

---

## 1. Deployments and Services

### Apply Deployment and Service
```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

### Verify Deployment and Service
```bash
kubectl get deployments   # List deployments
kubectl get services      # List services
```

### Scale Deployment
```bash
kubectl scale deployment notification-deployment --replicas=5
```

---

## 2. Ingress Setup

### Apply NGINX Ingress Controller
```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
```

### Apply Ingress Configuration
```bash
kubectl apply -f ingress.yaml
```

### Edit Hosts File (Windows)
1. Open **Notepad** as Administrator  
2. Open file: `C:\Windows\System32\drivers\etc\hosts`  
3. Add line:  
```
127.0.0.1 dev.sambathreasmey.local
```
4. Save changes  
5. If cached, flush DNS:
```bash
ipconfig /flushdns
```

### Test Ingress
Open in browser or use curl:
```
http://dev.sambathreasmey.com/api/notification
```

---

## 3. ConfigMap

### Apply ConfigMap
```bash
kubectl apply -f configMap.yaml
```

### Verify ConfigMap
```bash
kubectl describe configMap notification-config
```

### Mount ConfigMap in Deployment
Add the following to your `deployment.yaml`:
```yaml
spec:
  containers:
    - name: notification
      image: sambathreasmey/notification:1.0
      ports:
        - containerPort: 8080
      volumeMounts:
        - name: notification-config
          mountPath: /app/config
          readOnly: true
  volumes:
    - name: notification-config
      configMap:
        name: notification-config
```

### Restart Deployment
```bash
kubectl rollout restart deployment notification-deployment
```

---

## 4. Auto Reload ConfigMap (Optional)

### Apply Reloader
```bash
kubectl apply -f https://raw.githubusercontent.com/stakater/Reloader/master/deployments/kubernetes/reloader.yaml
```

### Add Annotation to Deployment
```yaml
metadata:
  annotations:
    reloader.stakater.com/auto: "true"
```
This enables automatic reload of the deployment whenever the referenced ConfigMaps are updated.

---

## 5. Delete Resources

```bash
kubectl delete deployment notification-deployment
kubectl delete service notification-service
kubectl delete configmap notification-config
kubectl delete ingress notification-ingress
```

---

## Notes

- Make sure the Docker image tag matches your pushed version.
- ConfigMaps should be updated carefully; use Reloader for automatic deployment updates.
- Scale deployments based on your cluster capacity and load requirements.

Thank you 🙏🏻

## **Sambath Reasmey**
*Junior Software Engineer | Backend & API Developer*

- 💡 **Project Initiator & Core Contributor** – Led the project from inception and remains a primary contributor  
- 🧱 **System Architecture & Technical Direction** – Designed the overall system structure and guided technical decisions  
- ☁️ **Infrastructure & Deployment** – Hands-on experience with OpenShift and containerized environments  
- 🤖 **Automation** – Built automation for build, deployment, and operational workflows  
- 🔌 **Backend APIs** – Developed and maintained scalable backend service APIs  
- 📊 **Observability & Monitoring** – Implemented monitoring and observability to ensure reliability and performance  
- 🚗 **Data Analysis** – Worked with driving pattern data and related system logic  
- 🛠️ **Continuous Improvement** – Actively maintains, optimizes, and evolves the project

GitHub: [@sambathreasmey](https://github.com/sambathreasmey) |
GitLab: [@sambathreasmey](https://gitlab.com/reasmeysambath) |
Linkedin: [@sambathreasmey](https://www.linkedin.com/in/reasmey-sambath-4b7658350) |
Telegram: [@sambathreasmey](https://t.me/sambathreasmey)
