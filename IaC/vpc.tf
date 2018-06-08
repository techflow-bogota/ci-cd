resource "aws_vpc" "techflow-vpc" {
  cidr_block           = "${var.aws_vpc_cidr}"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags {
    "Name"         = "${var.name_header}vpc"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}
