# Infrastructure as a Code Demo

## Requisites
Terraform and AWS Cli installed and configured (aws configure)

## Deployment
This code will setup a complete environment on AWS with thi components

![Alt text](graph.svg?raw=true "Graph generated with terraform")

  * VPC
  * Subnets (public and private)
  * IGW
  * NAT GW
  * Route Tables
  * 4 EC2 instances (2 for Frondend and 2 for Backend)
  * SG (Security Groups)

After deploy the terraform plan proceed to configure with Ansible playbook (don't forget update the inventory first)

```code
$terraform init
$terraform plan
$terraform apply
```
