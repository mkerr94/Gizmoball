import javax.swing.*;

public class FileChooser
{
    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents/PortfolioManager/";

    public FileChooser()
    {
        fc = new JFileChooser(FILE_PATH);

    }


    /*public IPortfolio getOpenedPortfolio() throws IOException, ClassNotFoundException {
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            return PortfolioSerializer.openFromFile(file);

        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
        }
        // avoid returning null
        return null;
    }*/
}
