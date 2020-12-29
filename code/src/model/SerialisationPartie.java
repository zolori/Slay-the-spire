package model;

import java.io.*;
import java.util.ArrayList;

public interface SerialisationPartie {
    public void serialisation(ObjectOutputStream oos);
    public void deserialisation(ObjectInputStream ois);



}



