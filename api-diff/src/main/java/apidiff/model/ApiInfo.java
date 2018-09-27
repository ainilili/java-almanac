package apidiff.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ApiInfo extends ElementInfo implements Consumer<ClassInfo> {

	private final Map<String, PackageInfo> packages;

	public ApiInfo(String name) {
		super(name);
		this.packages = new HashMap<>();
	}

	@Override
	public ElementType getType() {
		return ElementType.API;
	}

	@Override
	public String getDisplayName() {
		return getName();
	}

	@Override
	public void accept(ClassInfo c) {
		PackageInfo pkg = packages.computeIfAbsent(c.getPackageName(), name -> new PackageInfo(name, c.getModule()));
		pkg.addClass(c);
	}

	@Override
	public List<ElementInfo> getChildren() {
		List<ElementInfo> children = new ArrayList<>();
		children.addAll(packages.values());
		return children;
	}

}
