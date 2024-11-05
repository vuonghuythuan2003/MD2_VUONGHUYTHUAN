package ra.entity;

import ra.business.CategoriesBusiness;
import ra.business.ProductBusiness;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Product implements IStoreManagement {
    private int productid;
    private String productname;
    private int stock;
    private double costPrice;
    private double sellPrice;
    private Date createdAt;
    private int categoryid;

    public Product() {
    }

    public Product(int productid, String productname, int stock, double costPrice, double sellPrice, Date createdAt, int categoryid) {
        this.productid = productid;
        this.productname = productname;
        this.stock = stock;
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
        this.createdAt = createdAt;
        this.categoryid = categoryid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.productname = inputProductName(scanner);
        this.stock = inputStock(scanner);
        this.costPrice = inputCost_price(scanner);
        this.sellPrice = inputSelling_price(scanner);
        this.categoryid = inputIdCategory(scanner);
        this.createdAt = inputDate(scanner);
    }

    public String inputProductName(Scanner scanner) {
        System.out.println("Nhập tên sản phẩm: ");
        List<Product> listProduct = ProductBusiness.findAll();
        do {
            String inputName = scanner.nextLine();
            if (inputName.isEmpty()) {
                System.err.println("Tên sản phẩm không được để trống");
            } else {
                boolean isDuplicated = false;
                for (Product product : listProduct) {
                    if (inputName.equals(product.getProductname())) {
                        System.err.println("Ten sản pham da ton tai");
                        isDuplicated = true;
                        break;
                    }
                }
                if (!isDuplicated) {
                    return inputName;
                }
            }
        } while (true);
    }

    public static int inputStock(Scanner scanner) {
        System.out.println("Nhập vào số lượng sản phẩm: ");
        do {
            int inputStock = Integer.parseInt(scanner.nextLine());
            if (inputStock <= 0) {
                System.out.println("Số lượng sản phẩm phải lớn hơn 0");
            } else {
                return inputStock;
            }
        } while (true);
    }

    public static int inputCost_price(Scanner scanner) {
        System.out.println("Nhập vào giá nhập sản phẩm: ");
        do {
            int inputCost_price = Integer.parseInt(scanner.nextLine());
            if (inputCost_price <= 0) {
                System.out.println("Gía nhập sản phẩm phải lớn hơn 0");
            } else {
                return inputCost_price;
            }
        } while (true);
    }

    public static int inputSelling_price(Scanner scanner) {
        System.out.println("Nhập vào giá bán sản phẩm: ");
        do {
            int inputSelling_price = Integer.parseInt(scanner.nextLine());
            if (inputSelling_price <= 0) {
                System.out.println("Gía bán sản phẩm phải lớn hơn 0");
            } else {
                return inputSelling_price;
            }
        } while (true);

    }

    public static int inputIdCategory(Scanner scanner) {
        List<Categories> listCategories = CategoriesBusiness.findAll();
        System.out.println("Mời bạn chọn mã sản phẩm");
        for (Categories category : listCategories) {
            System.out.printf("Mã danh mục %d - %s\n", category.getCategoryId(), category.getCategoryName());
        }
        System.out.println("Lựa chọn của bạn");
        do {
            try {
                int inputIdCategory = Integer.parseInt(scanner.nextLine());
                Categories category = CategoriesBusiness.findById(inputIdCategory);
                if (category == null) {
                    System.err.println("Mã danh mục không tồn tại: ");
                } else {
                    return inputIdCategory;
                }
            } catch (NumberFormatException e) {
                System.err.println("Nhập vào mã danh mục là số");
            }
        } while (true);
    }

    public static Date inputDate(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date inputDate = null;
        while (true) {
            System.out.println("Nhập vào ngày tháng năm: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.err.println("Ngày không đươc để trống");
            } else {
                try {
                    inputDate = dateFormat.parse(input);
                    return inputDate;
                } catch (ParseException ex) {
                    System.err.println("Định dạng không hợp lệ");
                }
            }
        }
    }

    @Override
    public void displayData() {
        System.out.println("Danh sách sản phẩm");
        System.out.println("Mã sản phẩm : " + this.productid);
        System.out.println("Tên sản phẩm : " + this.productname);
        System.out.println("Số lượng sản phẩm: " + this.stock);
        System.out.println("Giá nhập sản phẩm: " + this.costPrice);
        System.out.println("Giá bán sản phẩm: " + this.sellPrice);
        System.out.println("Ngày tạo: " + this.createdAt);
        System.out.println("MÃ DANH MỤC: " + this.categoryid);

    }
}
