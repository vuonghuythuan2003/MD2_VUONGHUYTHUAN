package ra.entity;

import ra.business.CategoriesBusiness;

import java.util.List;
import java.util.Scanner;

public class Categories implements IStoreManagement {
    private int categoryId;
    private String categoryName;
    private boolean categoryStatus;

    public Categories() {
    }

    public Categories(int categoryId, String categoryName, boolean categoryStatus) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.categoryName = inputCategoryName(scanner);
    }

    public String inputCategoryName(Scanner scanner) {
        System.out.println("Nhập tên danh mục: ");
        List<Categories> listCategories = CategoriesBusiness.findAll();
        do {
            String inputName = scanner.nextLine();
            if (inputName.isEmpty()) {
                System.err.println("Tên danh mục không được để trống");
            } else{
                boolean isDuplicated = false;
                for(Categories category : listCategories) {
                    if(inputName.equals(category.getCategoryName())) {
                        System.err.println("Ten danh muc da ton tai");
                        isDuplicated = true;
                        break;
                    }
                }
                if(!isDuplicated) {
                    return inputName;
                }
            }
        } while (true);

    }

    @Override
    public void displayData() {
        System.out.println("Thông tin danh mục");
        System.out.println("Category ID: " + this.categoryId);
        System.out.println("Category Name: " + this.categoryName);
        System.out.println("Category Status: " + (this.categoryStatus ? "Không hoạt đông" : " Hoạt động"));

    }
}
