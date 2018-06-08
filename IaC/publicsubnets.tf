resource "aws_subnet" "techflow-public-subnet-1" {
  vpc_id                  = "${aws_vpc.techflow-vpc.id}"
  cidr_block              = "${var.aws_public_subnet_1_cidr}"
  map_public_ip_on_launch = true
  availability_zone       = "us-east-1a"

  tags {
    "Name"         = "${var.name_header}public-subnet-1"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}

resource "aws_subnet" "techflow-public-subnet-2" {
  vpc_id                  = "${aws_vpc.techflow-vpc.id}"
  cidr_block              = "${var.aws_public_subnet_2_cidr}"
  map_public_ip_on_launch = true
  availability_zone       = "us-east-1b"

  tags {
    "Name"         = "${var.name_header}public-subnet-2"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}
