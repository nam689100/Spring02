pipeline {
    agent any
    
    tools {
        maven 'my-maven'  // 젠킨스에서 설치한 이름
    }
    environment {
        APP_NAME = 'ex02-app'
        DOCKER_TAG = 'latest'
        IMAGE_NAME = "belokana/${APP_NAME}:${DOCKER_TAG}"
        TARGET_HOST = '192.168.56.107'
        TARGET_USER = 'vagrant'
        PORT = '8081'
    }
    stages {
        stage('0. 자동화 확인1') { steps { echo '스테이지 출발' } }
        
        stage('1. Build') {
            steps {
                echo 'Maven으로 빌드 시작'
                sh 'mvn clean package'
            }
        }
        
        stage('2. Check Docker') {
            steps {
                sh 'docker version'
            }
        }
        
        stage('3. Docker Build') {
            steps {
                sh 'docker build -t ex02-app:latest .'
            }
        }

        stage('4. Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-cred',
                    usernameVariable: 'DOCKERHUB_USERNAME',
                    passwordVariable: 'DOCKERHUB_PASSWORD'
                )]) {
                    sh '''
                    echo "$DOCKERHUB_PASSWORD" | docker login -u "$DOCKERHUB_USERNAME" --password-stdin
                    docker tag ex02-app:latest $DOCKERHUB_USERNAME/ex02-app:latest
                    docker push $DOCKERHUB_USERNAME/ex02-app:latest
                    '''
                }
            }
        }
        stage('5. Deploy to vm7') {
            steps {
                sh '''
                    ssh -o StrictHostKeyChecking=no $TARGET_USER@$TARGET_HOST <<EOF
                        # 이미지 pull 실패 시 즉시 스크립트 종료
                        docker pull $IMAGE_NAME || exit 1
                        # 기존 컨테이너 제거, 없을 경우 에러 무시
                        docker rm -f $APP_NAME 2>/dev/null || true
                        docker run -d -p $PORT:$PORT --name $APP_NAME $IMAGE_NAME
EOF
                '''
            }
        }        
    }
}


















