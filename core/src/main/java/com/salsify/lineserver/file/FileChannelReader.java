package com.salsify.lineserver.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileChannelReader extends FileReader {
    private final FileChannel fileChannel;
    private final Charset fileCharset;

    private long fileChannelPointer = 0;

    private CharBuffer readerBuffer;
    private int readerBufferCapacity = 1024 * 4;

    protected FileChannelReader(FileChannel fileChannel, Charset charset) {
        this.fileChannel = fileChannel;
        this.fileCharset = charset;
    }

    public static FileReader create(Path pathToFile, Charset charset)
        throws FileReaderException {

        try {
            FileChannel channel = FileChannel.open(pathToFile, StandardOpenOption.READ);

            return new FileChannelReader(channel, charset);

        } catch (IOException e) {
            throw new FileReaderException("Could not create file channel", e);
        }
    }

    @Override
    public long getFilePointer()
            throws FileReaderException {

        try {
            return fileChannelPointer + getReaderBufferPosition();
        } catch (Exception e) {
            throw new FileReaderException("Could not get file channel position", e);
        }
    }

    private long getReaderBufferPosition() {
        return readerBuffer == null? 0 : readerBuffer.position();
    }

    @Override
    public Character read()
            throws FileReaderException {

        return readFromBuffer();
    }

    private Character readFromBuffer()
            throws FileReaderException {

        if (bufferNeedsUpdate())
            updateReaderBuffer();

        if (readerBuffer.remaining() > 0)
            return readerBuffer.get();

        return null;
    }

    private boolean bufferNeedsUpdate() {
        return readerBuffer == null || readerBuffer.remaining() == 0;
    }

    private void updateReaderBuffer()
            throws FileReaderException {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(readerBufferCapacity);

            fileChannelPointer = fileChannel.position();
            int bytesRead = fileChannel.read(byteBuffer);
            if (bytesRead > 0) {
                byteBuffer.rewind();
                readerBuffer = fileCharset.decode(byteBuffer);
            }
        }
        catch (Exception e) {
            throw new FileReaderException("Could not read from channel", e);
        }
    }

    @Override
    public String read(long startPosition, long endPosition)
            throws FileReaderException {
        try {
            String result = "";
            int resultLength = Long.valueOf(endPosition - startPosition).intValue();

            ByteBuffer byteBuffer = ByteBuffer.allocate(resultLength);

            int bytesRead = fileChannel.read(byteBuffer, startPosition);

            if (bytesRead > 0) {
                byteBuffer.rewind();

                result = fileCharset
                            .decode(byteBuffer)
                            .toString();
            }

            return result;
        } catch (Exception e) {
            throw new FileReaderException("Could not read start position to end position from channel", e);
        }
    }

    @Override
    public void rewind()
            throws FileReaderException {

        try {
            fileChannel.position(0);
        }
        catch(Exception e) {
            throw new FileReaderException("Could not rewind the reader", e);
        }
    }

    @Override
    public void close() throws IOException {
        readerBuffer = null;
        fileChannel.close();
    }
}
