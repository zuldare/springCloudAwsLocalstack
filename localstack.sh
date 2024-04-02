#!/bin/bash
# create local bucket
awslocal s3api create-bucket --bucket mybucket --create-bucket-configuration LocationConstraint=eu-central-1 && awslocal sqs create-queue --queue-name test-queue --region eu-central-1