package org.example.util;

import org.example.model.Warehouse;

import java.io.*;
import java.util.List;

public class SerializationUtil {

    public static void serialize(List<Warehouse> warehouses, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(warehouses);
        }
    }

    public static List<Warehouse> deserialize(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Warehouse>) ois.readObject();
        }
    }
}
