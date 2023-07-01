package snippet_splitter_out.ds_6;
public class ds_6_snip_5$CarReport_downloadFile {
// me.kuehle.carreport.util.sync.provider.DropboxSyncProvider.downloadFile()
// @Override // Removed to allow compilation
// SNIPPET_STARTS
public void downloadFile() throws SyncIoException, SyncParseException {
    File localFile = getLocalFile();
    File tempFile = new File(Application.getContext().getCacheDir(), getClass().getSimpleName());
    FileOutputStream outputStream = null;
    try {
        outputStream = new FileOutputStream(tempFile);
        mDbxClient.files().download("/" + localFile.getName()).download(outputStream);
        if (!FileCopyUtil.copyFile(tempFile, localFile)) {
            throw new IOException();
        }
    } catch (NetworkIOException e) {
        throw new SyncIoException(e);
    } catch (DbxException | IOException e) {
        throw new SyncParseException(e);
    } finally {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close output stream after downloading file.", e);
            }
        }
        if (!tempFile.delete()) {
            Log.w(TAG, "Could not delete temp file after downloading.");
        }
    }
}
}