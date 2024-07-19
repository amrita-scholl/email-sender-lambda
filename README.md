# email-sender-lambda

Command to deploy Lambda from cli
-------------------------------------
aws lambda create-function --function-name EmailSender --zip-file fileb://target/emailsender-1.0-SNAPSHOT.jar --handler com.email.EmailSenderLambda --runtime java21 --role arn:aws:iam::<accountnumber>:role/allowlambdarole
