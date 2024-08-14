package org.dashia18.storage.repo;

import org.dashia18.storage.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<File, Long> {
}
