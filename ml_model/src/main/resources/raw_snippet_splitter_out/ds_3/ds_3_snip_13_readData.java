package snippet_splitter_out.ds_3;
public class ds_3_snip_13_readData {
public void readData(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int version = stream.readInt();
        mNumber = stream.readInt();
        mName = stream.readUTF();
    }
}