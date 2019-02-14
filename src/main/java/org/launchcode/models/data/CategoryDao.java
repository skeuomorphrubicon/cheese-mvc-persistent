package org.launchcode.models.data;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
