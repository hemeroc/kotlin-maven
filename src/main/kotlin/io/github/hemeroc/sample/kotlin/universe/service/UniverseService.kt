package io.github.hemeroc.sample.kotlin.universe.service

/**
 * The `UniverseService` is capable to calculate the answer to
 * <blockquote>Ultimate Question of Life, the Universe, and Everything</blockquote>.
 * Depending on the implementation it might take a while.
 */
interface UniverseService {

    /**
     * Calculate the answer to the ultimate question of life, the universe, and everything.
     *
     * @return the answer to the ultimate question of life, the universe, and everything
     */
    fun calculateAnswer(): String
}
