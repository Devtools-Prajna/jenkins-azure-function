pipeline {
    agent any

    tools {
        maven 'M398'  // Ensure this is configured in Jenkins Global Tools
    }

    environment {
        AZURE_CREDENTIALS = credentials('AZURE_FUNCTION_PUBLISH_PROFILE') // Jenkins secret
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Package') {
            steps {
                sh 'mvn clean package azure-functions:package'
            }
        }

        stage('Deploy to Azure Function') {
            steps {
                // Save publish profile to file
                writeFile file: 'publishProfile.publishSettings', text: "${AZURE_CREDENTIALS}"

                // Deploy using publish profile
                sh '''
                    mvn azure-functions:deploy \
                        -Dazure.functions.publish.profile=publishProfile.publishSettings \
                        -Dazure.functions.deploy.type=publish-profile
                '''
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
            sh 'rm -f publishProfile.publishSettings'
        }
    }
}
