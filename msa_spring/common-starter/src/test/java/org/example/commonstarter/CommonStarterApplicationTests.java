package org.example.commonstarter;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CommonStarterApplicationTests {

	@Test void sanity() { assertThat(1 + 1).isEqualTo(2); }

}
