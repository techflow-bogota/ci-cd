resource "aws_subnet" "techflow-private-subnet-1" {
  vpc_id = "${aws_vpc.techflow-vpc.id}"
  cidr_block = "${var.aws_private_subnet_1_cidr}"
  map_public_ip_on_launch = false
  availability_zone = "us-east-1c"

  tags {
    "Name" = "${var.name_header}private-subnet-1"
    "created_by" = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}

resource "aws_subnet" "techflow-private-subnet-2" {
  vpc_id = "${aws_vpc.techflow-vpc.id}"
  cidr_block = "${var.aws_private_subnet_2_cidr}"
  map_public_ip_on_launch = false
  availability_zone = "us-east-1d"

  tags {
    "Name" = "${var.name_header}private-subnet-2"
    "created_by" = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}
