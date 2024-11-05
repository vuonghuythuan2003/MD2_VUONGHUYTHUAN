package ra.presentation;

import ra.business.CategoriesBusiness;
import ra.business.ProductBusiness;
import ra.entity.Categories;
import ra.entity.Product;

import java.util.List;
import java.util.Scanner;

public class StoreManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***************STORE-MANAGEMENT************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println(" Lựa chọn của bạn ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayCategoryManagement(scanner);
                        break;
                    case 2:
                        displayProductManagement(scanner);
                        break;
                    case 3:
                        System.out.println("Thoát chương trình");
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1 đến 3");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số nguyên từ 1 đến 3");
            }

        } while (true);
    }

    public static void displayProductManagement(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("**************CATEGORY-MENU**********");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Tạo mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Hiển thị danh sách sản phẩm theo ngày tạo giảm dần");
            System.out.println("6. Tìm kiếm sản phẩm theo khoản giá bán.");
            System.out.println("7. Hiển thị top 3 sản phẩm có lợi nhuận cao nhất (lợi nhuận = giá bán - giá nhập)");
            System.out.println("8. Thoát");
            System.out.println("Lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        listProduct();
                        break;
                    case 2:
                        createProduct(scanner);
                        break;
                    case 3:
                        updateCategory(scanner);
                        break;
                    case 4:
                        deleteCategory(scanner);
                        break;
                    case 5:
                        break;
                    case 6:
                        isExit = false;
                        System.out.println("Thoát khỏi menu quản lý danh mục");
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1 đến 6");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Vui lòng nhập số nguyên từ 1 đến 6");
            }
        } while(isExit);
    }

    public static void listProduct(){
        List<Product> listProduct = ProductBusiness.findAll();

        if(listProduct == null || listProduct.isEmpty()){
            System.out.println("Sản phẩm trống hoặc không tải được");
        }
        else{
            System.out.println("Số lượng sản phẩm: " + listProduct.size());
            for(Product product : listProduct) {
                product.displayData();
            }
        }
    }
    public static void createProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner);

        boolean result = ProductBusiness.save(product);
        if(result) {
            System.out.println("Thêm mới sản phẩm thành công");
        } else{
            System.err.println("Thêm mới sản phẩm thất bại");
        }
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm bạn cần xóa: ");
        try{
            int categoryId = Integer.parseInt(scanner.nextLine());
            Categories categoryDelete = CategoriesBusiness.findById(categoryId);
            if(categoryDelete != null) {
                boolean result = CategoriesBusiness.deleteCategories(categoryId);
                if(result) {
                    System.out.println("Xóa thành công");
                }
                else{
                    System.err.println("Xóa thất bại");
                }
            } else{
                System.err.println("Mã danh mục không tồn tại, vui lòng nhập lại");
            }
        }catch (NumberFormatException ex) {
            System.err.println("Vui lòng nhập một số nguyên hợp lệ");
        }
    }

    public static void displayCategoryManagement(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("**************CATEGORY-MENU**********");
            System.out.println("1. Danh sách danh mục");
            System.out.println("2. Tạo mới danh mục");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("6. Thoát");
            System.out.println("Lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        listCategories();
                        break;
                    case 2:
                        createCategories(scanner);
                        break;
                    case 3:
                        updateCategory(scanner);
                        break;
                    case 4:
                        deleteCategory(scanner);
                        break;
                    case 5:
                        break;
                    case 6:
                        isExit = false;
                        System.out.println("Thoát khỏi menu quản lý danh mục");
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1 đến 6");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Vui lòng nhập số nguyên từ 1 đến 6");
            }
        } while(isExit);
    }
    public static void listCategories() {
        List<Categories> listCategories = CategoriesBusiness.findAll();

        if(listCategories == null || listCategories.isEmpty()){
            System.out.println("Danh sách danh mc trống hoặc không tải được");
        }
        else{
            System.out.println("Số lượng danh mục: " + listCategories.size());
            for(Categories category : listCategories) {
                category.displayData();
            }
        }
    }
    public static void createCategories(Scanner scanner) {
        Categories categories = new Categories();
        categories.inputData(scanner);

        boolean result = CategoriesBusiness.save(categories);
        if(result) {
            System.out.println("Thêm mới danh mục thành công");
        } else{
            System.err.println("Thêm mới danh mục thất bại");
        }
    }

    public static void updateCategory(Scanner scanner){
        System.out.println("Nhập vào mã danh mục cần cập nhât: ");
        try{
            int categoryId = Integer.parseInt(scanner.nextLine());
            Categories categoryUpdate = CategoriesBusiness.findById(categoryId);
            if(categoryUpdate != null) {
                boolean isExit = true;
                do{
                    System.out.println("1.Cập nhật tên danh mục");
                    System.out.println("2.Cập nhật trạng thái danh mục");
                    System.out.println("3.Thoát");
                    System.out.println("Lựa chọn của bạn");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập tên mới cho danh mục: ");
                            categoryUpdate.setCategoryName(scanner.nextLine());
                            break;
                            case 2:
                                System.out.println("Nhập trạng thái mới cho danh mục");
                                categoryUpdate.setCategoryStatus(Boolean.parseBoolean(scanner.nextLine()));
                                break;
                                case 3:
                                    isExit = false;
                                    break;
                                    default:
                                        System.err.println("Vui lòng nhập từ 1 đến 3");
                    }

                } while(isExit);
                boolean result = CategoriesBusiness.updateCategory(categoryUpdate);
                if(result) {
                    System.out.println("Cập nhật thành công");
                } else{
                    System.err.println("Cập nhật thất bại");
                }
            } else{
                System.err.println("Mã danh mục không tồn tại");
            }
        } catch (NumberFormatException ex) {
            System.err.println("Vui lòng nhập một số nguyên hợp lệ");
        }
    }
    public static void deleteCategory(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục bạn cần xóa: ");
        try{
            int categoryId = Integer.parseInt(scanner.nextLine());
            Categories categoryDelete = CategoriesBusiness.findById(categoryId);
            if(categoryDelete != null) {
                boolean result = CategoriesBusiness.deleteCategories(categoryId);
                if(result) {
                    System.out.println("Xóa thành công");
                }
                else{
                    System.err.println("Xóa thất bại");
                }
            } else{
                System.err.println("Mã danh mục không tồn tại, vui lòng nhập lại");
            }
        }catch (NumberFormatException ex) {
            System.err.println("Vui lòng nhập một số nguyên hợp lệ");
        }
    }


}