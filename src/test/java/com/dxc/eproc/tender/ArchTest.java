package com.dxc.eproc.tender;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.dxc.eproc.tender");

        noClasses()
            .that()
            .resideInAnyPackage("com.dxc.eproc.tender.service..")
            .or()
            .resideInAnyPackage("com.dxc.eproc.tender.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.dxc.eproc.tender.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
