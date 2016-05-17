package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.model.*;
public interface ShopRepository extends JpaRepository<Shop, String>
{

}
