package com.example.demo.model;

import lombok.*;

import java.awt.geom.Point2D;



@Setter
@Getter
@EqualsAndHashCode
public class BoundingBox {


    private int iD;
    private Point2D.Double point1;
    private Point2D.Double point2;
public BoundingBox(int iD,Point2D.Double point1, Point2D.Double point2){
    this.iD=iD;
    this.point1=point1;
    this.point2=point2;
}
}
