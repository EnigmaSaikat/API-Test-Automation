pipeline {
    agent any

    parameters {
        string(name: 'EMAIL_RECIPIENTS', defaultValue: 'subal.pramanik@weavers-web.com', description: 'Comma-separated email addresses')
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: '7329ae75-5d3a-4ffd-a715-f3feb27016f1',
                    url: 'https://gitlab.com/saikat.j.das/test-automation'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            allure includeProperties: false,
                   jdk: '',
                   results: [[path: 'target/allure-results']]

            emailext(
                subject: "Jenkins Build ${currentBuild.result}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8" />
                        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                        <title>Build Notification</title>
                        <style>
                            body, table, td, p {
                                margin: 0;
                                padding: 0;
                                font-family: Arial, sans-serif;
                                line-height: 1.5;
                                color: #333333;
                            }
                            body {
                                background-color: #f7f7f7;
                            }
                            .container {
                                max-width: 600px;
                                width: 100%;
                                margin: 0 auto;
                                background: #ffffff;
                                border-radius: 6px;
                                overflow: hidden;
                                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                            }
                            .header {
                                background: #0073e6;
                                color: #ffffff;
                                text-align: center;
                                padding: 20px;
                            }
                            .content {
                                padding: 20px;
                            }
                            h2 {
                                color: #0073e6;
                                margin-bottom: 10px;
                            }
                            a {
                                color: #0073e6;
                                text-decoration: none;
                                word-break: break-all;
                            }
                            pre {
                                background: #f4f4f4;
                                border-radius: 5px;
                                padding: 10px;
                                font-size: 13px;
                                overflow-x: auto;
                            }
                            @media only screen and (max-width: 600px) {
                                .content {
                                    padding: 15px;
                                }
                                h2 {
                                    font-size: 20px;
                                }
                                pre {
                                    font-size: 12px;
                                }
                            }
                        </style>
                    </head>
                    <body>
                        <table role="presentation" class="container" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="header">
                                    <h2>Build \${currentBuild.result}</h2>
                                </td>
                            </tr>
                            <tr>
                                <td class="content">
                                    <p><b>Job:</b> \${env.JOB_NAME}</p>
                                    <p><b>Build Number:</b> \${env.BUILD_NUMBER}</p>
                                    <p><b>Build URL:</b> <a href="\${env.BUILD_URL}">\${env.BUILD_URL}</a></p>
                                    <p><b>Allure Report:</b> <a href="\${env.BUILD_URL}allure">\${env.BUILD_URL}allure</a></p>
                                    <hr style="margin:20px 0;border:none;border-top:1px solid #ddd;">
                                    <p><b>Console Output (last 50 lines):</b></p>
                                    <pre>\${BUILD_LOG, maxLines=50}</pre>
                                </td>
                            </tr>
                        </table>
                    </body>
                    </html>
                """,
                mimeType: 'text/html',
                to: "${params.EMAIL_RECIPIENTS}",
                from: 'saikat.das@weavers-web.com',
                attachLog: true
            )
        }
    }
}