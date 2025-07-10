pipeline {
    agent any

    tools {
        maven 'M398'  // Must match name in Jenkins Global Tools config
    }

    environment {
        AZURE_CREDENTIALS = credentials('AZURE_FUNCTION_PUBLISH_PROFILE') // The publish profile as Jenkins secret text
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
                // Save the publish profile to a file
                writeFile file: 'publishProfile.publishSettings', text: "${AZURE_CREDENTIALS}"

                // Deploy using the publish profile
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
            echo 'Cleaning up sensitive files...'
            sh 'rm -f publishProfile.publishSettings'
        }
    }
}
