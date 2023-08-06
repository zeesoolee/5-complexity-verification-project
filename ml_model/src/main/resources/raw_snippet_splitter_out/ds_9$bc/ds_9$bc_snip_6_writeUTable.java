package snippet_splitter_out.ds_9$bc;
public class ds_9$bc_snip_6_writeUTable {
protected Table writeUTable(UTable uTable)
    {
    /* Define local variables. */
    Table table = new Table(uTable.getColumnsCount());
    BoxModelOption option = uTable.getBoxModel();
    UColor color = option.getBorderColor();
    table.setBorderColor(new Color
    (color.getRed(),color.getGreen(),color.getBlue()));
    Cell cell = null; // Added to allow compilation
    for (UTableCell uCell : uTable.getEntries()) {
    /* Define local variable. */
    UParagraph paragraph = uCell.getContent();
    UChildren children = paragraph.getChildren();
    /* If children size larger than 0 and first children is an image. */
    if (children.size() > 0 && children.get(0) instanceof UImage) {
    UImage image = children.get(0);
    Path path = image.getPath();
    USegment segment = path.lastSegment();
    /* If segment starts with false, then create a cell with “no”. */
    /* Otherwise, if segment starts with true, then a create cell */
    /* with “yes”. Otherwise create cell with segment. */
    if (segment.startsWith("false")) {
    cell = new Cell("no");
    } else {
    if (segment.startsWith("true")) {
    cell = new Cell("yes");
    } else {
    cell = new Cell(segment);
    }
    }
    /* Copy background color. */
    option = uCell.getBoxModel();
    color = option.getBackgroundColor();
    if (color != null) {
    cell.setBackgroundColor(color);
    }
    }
    table.addCell(cell);
    }
    return table;
    }
}