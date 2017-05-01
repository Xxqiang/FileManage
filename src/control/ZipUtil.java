package control;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * 通过Java的Zip输入输出流实现压缩和解压文件
 */
public final class ZipUtil {

    private ZipUtil() {

    }

    /**
     * 压缩文件
     *
     * @param filePath 待压缩的文件路径
     * @return 压缩后的文件
     */
    public static File zip(String filePath) {
        File target = null;
        File source = new File(filePath);
        if (source.exists()) {
            String sourceName = source.getName();
            String zipName = sourceName.contains(".") ? sourceName.substring(0, sourceName.indexOf(".")) : sourceName;
            target = new File(source.getParent(), zipName + ".rar");
            if (target.exists()) {
                boolean delete = target.delete();//删除旧的压缩包
            }
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            try {
                fos = new FileOutputStream(target);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));

                addEntry(null, source, zos);  //添加对应的文件Entry
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtil.closeQuietly(zos, fos);
            }
        }
        return target;
    }

    /**
     * 扫描添加文件Entry
     *
     * @param base   基路径
     * @param source 源文件
     * @param zos    Zip文件输出流
     * @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException {
        String entry = (base != null) ? base + source.getName() : source.getName(); //按目录分级，形如：aaa/bbb.txt
        if (source.isDirectory()) {
            File[] files = source.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    addEntry(entry + "/", file, zos);// 递归列出目录下的所有文件，添加文件 Entry
                }
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read;
                zos.putNextEntry(new ZipEntry(entry)); //如果只是想将文件夹下的所有文件压缩，不需名要压缩父目录,约定文件名长度 entry.substring(length)
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {
                IOUtil.closeQuietly(bis, fis);
            }
        }
    }

    /**
     * 解压文件
     *
     * @param filePath 压缩文件路径
     */
    public static void unzip(String filePath) {
        File source = new File(filePath);
        if (source.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                    File target = new File(source.getParent(), entry.getName());
                    if (!target.getParentFile().exists()) {  
                        target.getParentFile().mkdirs();
                    }
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtil.closeQuietly(zis, bos);
            }
        }
    }
}