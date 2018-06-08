resource "aws_internet_gateway" "techflow-igw" {
  vpc_id = "${aws_vpc.techflow-vpc.id}"

  tags {
    "Name"         = "${var.name_header}igw"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}
