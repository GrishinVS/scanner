package com.grishinvs.scanner;

import java.util.Objects;

public class ScanFile {

    private String path;
    private String dirName;
    private long lastModified;

    public ScanFile() {
    }

    public ScanFile(String path, long lastModified, String dirName) {
        this.path = path;
        this.lastModified = lastModified;
        this.dirName = dirName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScanFile myFile = (ScanFile) o;
        if (lastModified != myFile.lastModified) return false;
        if (!Objects.equals(path, myFile.path)) return false;
        return Objects.equals(dirName, myFile.dirName);
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (dirName != null ? dirName.hashCode() : 0);
        result = 31 * result + (int) (lastModified ^ (lastModified >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ScanFile{" +
                "path='" + path + '\'' +
                ", dirName='" + dirName + '\'' +
                ", lastModified=" + lastModified +
                '}';
    }

    public String getPath() {
        return path;
    }

    public long getLastModified() {
        return lastModified;
    }

    public String getDirName() {
        return dirName;
    }

}
