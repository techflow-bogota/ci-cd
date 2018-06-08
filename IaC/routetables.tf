resource "aws_default_route_table" "techflow-public-route-table" {
  default_route_table_id = "${aws_vpc.techflow-vpc.default_route_table_id}"

  tags {
    "Name"         = "${var.name_header}public-route-table"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}

resource "aws_route" "techflow-public-route" {
  route_table_id         = "${aws_vpc.techflow-vpc.main_route_table_id}"
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = "${aws_internet_gateway.techflow-igw.id}"
}

resource "aws_route_table" "techflow-private-route-table" {
  vpc_id = "${aws_vpc.techflow-vpc.id}"

  tags {
    "Name"         = "${var.name_header}private-route-table"
    "created_by"   = "${var.my_name}"
    "project_name" = "${var.project_name}"
  }
}

resource "aws_route" "techflow-private-route" {
  route_table_id         = "${aws_route_table.techflow-private-route-table.id}"
  destination_cidr_block = "0.0.0.0/0"
  nat_gateway_id         = "${aws_nat_gateway.techflow-ngw.id}"
}

resource "aws_route_table_association" "public-subnet-1-to-public-route-table" {
  subnet_id      = "${aws_subnet.techflow-public-subnet-1.id}"
  route_table_id = "${aws_vpc.techflow-vpc.main_route_table_id}"
}

resource "aws_route_table_association" "public-subnet-2-to-public-route-table" {
  subnet_id      = "${aws_subnet.techflow-public-subnet-2.id}"
  route_table_id = "${aws_vpc.techflow-vpc.main_route_table_id}"
}

resource "aws_route_table_association" "private-subnet-1-to-private-route-table" {
  subnet_id      = "${aws_subnet.techflow-private-subnet-1.id}"
  route_table_id = "${aws_route_table.techflow-private-route-table.id}"
}

resource "aws_route_table_association" "private-subnet-2-to-private-route-table" {
  subnet_id      = "${aws_subnet.techflow-private-subnet-2.id}"
  route_table_id = "${aws_route_table.techflow-private-route-table.id}"
}
