resource "aws_instance" "techflow-back-intances" {
  count         = 2
  instance_type = "t2.micro"
  ami           = "${lookup(var.aws_amis, var.aws_region)}"
  subnet_id     = "${aws_subnet.techflow-private-subnet-1.id}"
  key_name      = "techflow-backend"

  security_groups = [
    "${aws_vpc.techflow-vpc.default_security_group_id}",
  ]

  tags {
    "Name"         = "${var.name_header}back-${count.index+1}"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}
