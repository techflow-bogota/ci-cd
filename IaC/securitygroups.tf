resource "aws_default_security_group" "techflow-frontend-sg" {
  vpc_id = "${aws_vpc.techflow-vpc.id}"

  ingress {
    protocol    = -1
    from_port   = 0
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
    self        = false
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags {
    "Name"         = "${var.name_header}frontend-sg"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}
