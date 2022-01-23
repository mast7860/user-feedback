# Public Cloud Configuration
provider "aws" {
  region                      = "eu-west-1"
  access_key                  = "test"
  secret_key                  = "test"
  skip_credentials_validation = true
  skip_requesting_account_id  = true
  skip_metadata_api_check     = true
  s3_force_path_style         = true
  endpoints {
    ses      = "http://localhost:4566"
    sqs      = "http://localhost:4566"
    dynamodb = "http://localhost:4566"
  }
}

# Create SQS
resource "aws_sqs_queue" "feedback_queue" {
  name                       = "feedback-sqs"
  receive_wait_time_seconds  = 20
  fifo_queue                 = false
  message_retention_seconds  = 60
  visibility_timeout_seconds = 60
}

# Create dynamo db table

resource "aws_dynamodb_table" "feedback_table" {
  name         = "feedback"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "emailId"
  range_key    = "timeStampFeedback"

  attribute {
    name = "emailId"
    type = "S"
  }

  attribute {
    name = "timeStampFeedback"
    type = "S"
  }
}