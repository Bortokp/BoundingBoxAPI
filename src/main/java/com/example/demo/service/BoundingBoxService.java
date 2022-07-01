package com.example.demo.service;

import com.example.demo.model.BoundingBox;
import com.example.demo.model.BoundingBoxDTO;
import com.example.demo.repository.BoundingBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;


@Component
@RequiredArgsConstructor
public class BoundingBoxService {

    private final BoundingBoxRepository repository;
    @Value("${workspace.image.width}")
    private double width;
    @Value("${workspace.image.height}")
    private double height;
    @Value("${workspace.boundingBox.minWidth}")
    private double minWidth;
    @Value("${workspace.boundingBox.minHeight}")
    private double minHeight;

    public void addBoundingBox(double x1, double y1, double x2, double y2) throws Exception {
        if (x1 >= minWidth && y1 >= minHeight && x1 <= width && y1 <= height && x2 >= minWidth && y2 >= minHeight && x2 <= width && y2 <= height) {
            int iD = repository.calculateID();
            repository.addBoundingBox(new BoundingBox(iD, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2)));
        } else throw new Exception("");
    }

    public BoundingBoxDTO getAllBoundingBoxes() {

        return repository.getAllBoundingBoxes();
    }

    public void editBoundingBox(int iD, double x1, double y1, double x2, double y2) throws Exception {
        if (x1 >= minWidth && y1 >= minHeight && x1 <= width && y1 <= height && x2 >= minWidth && y2 >= minHeight && x2 <= width && y2 <= height && x1 <= x2 && y1 >= y2) {
            Point2D.Double tpoint1 = new Point2D.Double(x1, y1);
            Point2D.Double tpoint2 = new Point2D.Double(x2, y2);
            if (repository.getAllBoundingBoxes().getBoundingBoxes().containsKey(iD))
                repository.editBoundingBox(iD, tpoint1, tpoint2);
            else throw new Exception("");
        } else throw new Exception("");
    }


    public BoundingBox getBoundingBoxByID(int iD) throws Exception {
        if (repository.getAllBoundingBoxes().getBoundingBoxes().containsKey(iD)) {
            return repository.getBoundingBoxByID(iD);
        } else throw new Exception("");
    }

    public String configcheck() {
        return this.height + " " + this.width + " " + this.minHeight + " " + this.minWidth;
    }

    public void removeBB(int iD) throws Exception {
        if (repository.getAllBoundingBoxes().getBoundingBoxes().containsKey(iD)) {
            repository.removeBoundingBox(iD);
        } else throw new Exception("");
    }
}
