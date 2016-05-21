package helpers;

import helpers.property.GlobalPropertiesHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.tree.TreePath;

import objects.CustomTreeNode;
import cache.SessionCacheManager;
import cache.StructureCacheManager;

public class FileHelper {

	public static final String TEST_FILE_LOCATION = "E:\\Programming\\Eclipse Workspace\\NoteTree\\TEST_LOCATION";
	private static final String TEXTFILE_EXTENSION = ".txt";
	private static final String FILE_EXTENSION = ".tnt";
	public static final String STRUCTURE_NAME = "structure.xml";

	public static String getCurrentFileRootLocation() {
		return TEST_FILE_LOCATION;
	}

	public static String getFileContentAsString(String path) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			List<String> list = Files.readAllLines(Paths.get(path));
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				sb.append(it.next() + "\n");
			}
			result = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getCacheFolderLocation() {
		return getRootDirectory() + File.separator + "cache";
	}

	public static void saveToZip(String zipLocation) {
		TreePath[] treePaths = UIHelper.getTreeObject().getSelectionPaths();
		if (treePaths != null) {
			for (int i = 0; i < treePaths.length; ++i) {
				TreePath treePath = treePaths[i];
				CustomTreeNode node = (CustomTreeNode) treePath.getLastPathComponent();
				try {
					FileHelper.saveTextFile(node.getId(), (UIHelper.getTextArea().getText()));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
		try {
			FileHelper.saveStructure();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// Save the zip file
		try {
			ZipHelper.Zip(SessionCacheManager.getCacheFolderLocation(), zipLocation);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void saveTextFile(String textFileId, String content) throws FileNotFoundException {
		saveFile(SessionCacheManager.getCacheFolderLocation() + File.separator + textFileId + ".txt", content);
	}

	public static void saveFile(String path, String content) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(path);
		out.println(content);
		out.close();
	}

	public static void saveStructure() throws FileNotFoundException {
		List<CustomTreeNode> listOfNodes = StructureCacheManager.getNodesAsList();
		String saveData = XMLHelper.createStructureXML(listOfNodes);
		saveFile(SessionCacheManager.getCacheFolderLocation() + File.separator + "structure.xml", saveData);
	}

	public static String getRootDirectory() {
		return System.getProperty("user.dir");
	}

	public static boolean createNewFile(String location) {
		boolean result = false;
		File file = new File(location);
		if (!file.exists()) {
			try {
				result = file.createNewFile();
			} catch (IOException e) {
				result = false;
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void deleteOpenCache() {
		String cacheLocation = SessionCacheManager.getCacheFolderLocation();
		File cacheFolder = new File(cacheLocation);
		deleteFolder(cacheFolder);
	}

	private static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	public static String getNodePath(CustomTreeNode node) {
		return GlobalPropertiesHelper.getWorkingCopyCacheLocation() + File.separator + SessionCacheManager.getProjectName() + File.separator + node.getId()
				+ TEXTFILE_EXTENSION;
	}

	public static String getFileName(String name) {
		String result = name;
		int index = name.lastIndexOf(FILE_EXTENSION);
		if (index < 0) {
			result = result + FILE_EXTENSION;
		}
		return result;
	}

	// TODO: create "new" button
	public static String createNewCache() throws FileNotFoundException {
		String result = null;
		int number = ((int) System.currentTimeMillis());
		File folder = new File(getCacheFolderLocation() + File.separator + number);
		while (folder.exists()) {
			Random random = new Random();
			folder = new File(SessionCacheManager.getCacheFolderLocation() + File.separator + (number * random.nextInt()));
		}
		boolean success = folder.mkdirs();
		if (success) {
			String structureContents = XMLHelper.createStructure();
			String structurePath = folder.getPath() + File.separator + STRUCTURE_NAME;
			saveFile(structurePath, structureContents);
			result = folder.getPath();
		}
		return result;

	}

	public static void deleteNodeFromCache(CustomTreeNode node) throws FileNotFoundException {
		String loc = getNodePath(node);
		new File(loc).delete();
		saveStructure();
	}

	public static String getPropertyFilesLocation() {
		return getRootDirectory() + File.separator + "properties" + File.separator;
	}

}
