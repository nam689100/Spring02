pipeline {
    agent any
    
    tools {
        maven 'my-maven'
    }

    stages {
        stage('0. 연결 확인9') { steps { echo '스테이지 출발' } }
        
        stage('1. Build') {
            steps {
                echo 'Maven으로 빌드 시작'
                sh 'mvn clean package'
            }
        }        
        stage('2. docker 버전 확인') {
            steps {
                sh 'docker version'
            }
        }
        stage('3.Docker Build') {
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
EOF # 반드시 맨앞에
                '''
            }
        }        
    }
}