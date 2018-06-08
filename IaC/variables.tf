variable "my_name" {
  default = "lucham"
}

variable "name_header" {
  default = "techflow-"
}

variable "keyname" {
  default = "techflow-frontend"
}

variable "project_name" {
  default = "Endava TechFlow"
}

variable "aws_region" {
  description = "AWS region to launch servers."
  default     = "us-east-1"
}

variable "aws_vpc_cidr" {
  default = "10.0.0.0/16"
}

variable "aws_public_subnet_1_cidr" {
  default = "10.0.1.0/24"
}

variable "aws_public_subnet_2_cidr" {
  default = "10.0.2.0/24"
}

variable "aws_private_subnet_1_cidr" {
  default = "10.0.3.0/24"
}

variable "aws_private_subnet_2_cidr" {
  default = "10.0.4.0/24"
}

# Amazon Linux 2 LTS Candidate AMI 2017.12.0 (HVM), SSD Volume Type
variable "aws_amis" {
  default = {
    us-east-1 = "ami-428aa838"
    us-east-2 = "ami-710e2414"
  }
}
