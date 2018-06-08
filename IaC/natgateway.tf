resource "aws_eip" "techflow-ngw-eip" {
  vpc        = true
  depends_on = ["aws_internet_gateway.techflow-igw"]

  tags {
    "Name"         = "${var.name_header}ngw-eip"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}

resource "aws_nat_gateway" "techflow-ngw" {
  subnet_id     = "${aws_subnet.techflow-public-subnet-1.id}"
  allocation_id = "${aws_eip.techflow-ngw-eip.id}"
  depends_on    = ["aws_internet_gateway.techflow-igw"]

  tags {
    "Name"         = "${var.name_header}ngw"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}
