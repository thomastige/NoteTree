package objects;

import helpers.property.GlobalPropertiesHelper;

import java.util.Map;

public class LocalizedString {
	private Map<String, String> translations;
	private String id;

	public LocalizedString(String id, Map<String, String> translationMap) {
		this.id = id;
		this.translations = translationMap;
	}

	public LocalizedString(String id) {
		this.id = id;
	}

	public String getLocalizedString(String language) {
		String result = null;
		if (translations != null){
			result = translations.get(language);
		}
		if (result == null){
			result = id;
		}
		return result;
	}

	public String getLocalizedString() {
		return getLocalizedString(GlobalPropertiesHelper.getLanguage());
	}

}
