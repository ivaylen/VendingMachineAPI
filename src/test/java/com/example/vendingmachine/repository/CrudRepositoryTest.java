package com.example.vendingmachine.repository;

import com.example.vendingmachine.enumeration.Coin;
import com.example.vendingmachine.model.Product;
import com.example.vendingmachine.service.implementation.VendingMachineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static com.example.vendingmachine.enumeration.ProductType.COCA_COLA;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudRepositoryTest {
    @Mock
    private InventoryRepository inventoryRepository;
    private VendingMachineServiceImpl underTest;

    @BeforeEach
    void setUp(){
        underTest = new VendingMachineServiceImpl(inventoryRepository);
    }

    @Test
    void canAddProduct() {
        //given
        Product product = new Product(4L, "Cola", COCA_COLA, Coin.TEN_ST);
        //when
        underTest.saveProduct(product);
        //then
        ArgumentCaptor<Product> productArgumentCaptor
                = ArgumentCaptor.forClass(Product.class);

        verify(inventoryRepository)
                .add(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void canGetAllProducts() {
        //when
        underTest.getAllProducts();
        //then
        verify(inventoryRepository).retrieveAll();
    }
    @Test
    void whenSaveProductShouldReturnProduct() {
        Product product = new Product(4L, "Cola", COCA_COLA, Coin.TEN_ST);
        when(inventoryRepository.add(ArgumentMatchers.any(Product.class))).thenReturn(product);
        Product created = underTest.saveProduct(product);
        assertThat(created.getName()).isSameAs(product.getName());
        //then
        verify(inventoryRepository).add(product);
    }
}