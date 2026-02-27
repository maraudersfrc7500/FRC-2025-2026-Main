package frc.robot.apriltags;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class TagReader {
    public Pose2d getTagPosition(int tagId) throws Exception {
        CSVReader reader = new CSVReaderBuilder(new FileReader("2026-rebuilt-andymark.csv")).build();
        List<String[]> tagLocs = reader.readAll();
        String[] pos = tagLocs.get(tagId);
        double xpos = Double.parseDouble(pos[1]);
        double ypos = Double.parseDouble(pos[2]);
        double heading = Math.toRadians(Double.parseDouble(pos[5]));
        return new Pose2d(xpos,ypos,new Rotation2d(heading));
    }
}
