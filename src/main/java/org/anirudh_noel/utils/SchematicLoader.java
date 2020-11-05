package org.anirudh_noel.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.anirudh_noel.Start;
import org.anirudh_noel.model.PocketBlockTypes;

public class SchematicLoader {

	private Start plugin;

	public SchematicLoader(Start plugin) {
		this.plugin = plugin;
	}

	public void setupDataFolder() throws IOException {

		final File schematicFolder = new File(plugin.getDataFolder().getAbsoluteFile() + "/schematics");

		if (!schematicFolder.exists()) {
			schematicFolder.mkdirs();
			initDefaultSchematics(schematicFolder.getAbsolutePath());
		} else {
			initDefaultSchematics(schematicFolder.getAbsolutePath());
			// init schematics from users
		}
	}

	private void copyFile(InputStream fis, File out) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(out)) {
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		}
	}

	private void initDefaultSchematics(String schematicServerPath) throws IOException {

		for (PocketBlockTypes pocketBlockType : PocketBlockTypes.values()) {

			String resourcePath = "/schematics/" + pocketBlockType.getSchematicPath();

			final File defaultCanonSchematic = new File(schematicServerPath + "/" + pocketBlockType.getSchematicPath());

			if (defaultCanonSchematic.createNewFile()) {

				copyFile(getClass().getResourceAsStream(resourcePath), defaultCanonSchematic);

			}
		}
	}

}
