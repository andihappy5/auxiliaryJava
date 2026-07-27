package com.happy.graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphQLItemRepository extends JpaRepository<GraphQLItem, Long> {
}
