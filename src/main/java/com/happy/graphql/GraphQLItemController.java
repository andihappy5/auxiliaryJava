package com.happy.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLItemController {

    private final GraphQLItemRepository repository;

    public GraphQLItemController(GraphQLItemRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<GraphQLItem> items() {
        return repository.findAll();
    }

    @QueryMapping
    public GraphQLItem itemById(@Argument Long id) {
        return repository.findById(id).orElse(null);
    }

    @MutationMapping
    public GraphQLItem createItem(@Argument String name, @Argument String description) {
        GraphQLItem item = new GraphQLItem(name, description);
        return repository.save(item);
    }

    @MutationMapping
    public GraphQLItem updateItem(@Argument Long id, @Argument String name, @Argument String description) {
        return repository.findById(id)
                .map(existing -> {
                    if (name != null && !name.isBlank()) {
                        existing.setName(name);
                    }
                    if (description != null) {
                        existing.setDescription(description);
                    }
                    return repository.save(existing);
                })
                .orElse(null);
    }

    @MutationMapping
    public Boolean deleteItem(@Argument Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
