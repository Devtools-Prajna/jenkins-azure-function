pipeline {
    agent any

    environment {
        AZURE_WEBAPP_NAME = 'mypython' // Your Azure Web App name
        AZURE_REGION_SUFFIX = 'canadacentral-01' // Azure region suffix
        AZURE_CREDENTIAL_ID = 'azure-webapp-publish-profile' // Jenkins credential ID
        ZIP_NAME = 'flaskapp.zip'
        VENV = 'venv'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Devtools-Prajna/jenkins-repo.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh '''
                    python3 -m venv ${VENV}
                    . ${VENV}/bin/activate
                    pip install -r requirements.txt
                '''
            }
        }

        stage('Package App') {
            steps {
                sh '''
                    zip -r ${ZIP_NAME} app.py requirements.txt -x "${VENV}/*" ".git/*"
                '''
            }
        }

        stage('Deploy to Azure') {
            steps {
                withCredentials([file(credentialsId: "${AZURE_CREDENTIAL_ID}", variable: 'PUBLISH_PROFILE')]) {
                    sh '''
                        USERNAME=$(grep userName ${PUBLISH_PROFILE} | sed 's/.*="\\(.*\\)".*/\\1/')
                        PASSWORD=$(grep userPWD ${PUBLISH_PROFILE} | sed 's/.*="\\(.*\\)".*/\\1/')
                        DEPLOY_URL="https://${AZURE_WEBAPP_NAME}.scm.${AZURE_REGION_SUFFIX}.azurewebsites.net/api/zipdeploy"

                        echo "[INFO] Deploying to $DEPLOY_URL..."
                        curl -X POST -u $USERNAME:$PASSWORD --data-binary @${ZIP_NAME} -H "Content-Type: application/zip" $DEPLOY_URL
                    '''
                }
            }
        }
    }
}
