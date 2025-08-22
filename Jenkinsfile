pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'prajnashetty529/java-app'
        SONAR_PROJECT_KEY = 'Register-App'
        SONAR_HOST_URL = 'http://20.55.91.91:9000/'
    }

    tools {
        maven 'Maven3'  // Ensure Maven is configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean install'
            }
        }


        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:latest .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push $DOCKER_IMAGE:latest
                    """
                }
            }
        }

        stage('Trivy Scan') {
            steps {
                sh """
                trivy image --exit-code 1 --severity HIGH,CRITICAL --format json --output trivy-report.json $DOCKER_IMAGE:latest || echo "Vulnerabilities found"
                """
            }
        }

        stage('Deploy to AKS') {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig-secret', variable: 'KUBECONFIG')]) {
                    sh '''
                    kubectl get node
                    kubectl apply -f k8s/deployment.yaml
                    kubectl apply -f k8s/service.yaml
                    '''
                }
            }
        }
    }
}
