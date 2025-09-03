/**
 * Dieses Package definiert die grundlegenden grafischen Formen (Shapes) für das Hausprojekt.
 * <p>
 * Enthaltene Klassen:
 * <ul>
 *   <li>{@code Square}, {@code Triangle}, {@code Circle} – einfache geometrische Grundformen</li>
 *   <li>{@code Cloud}, {@code Person} – zusammengesetzte oder animierte Shapes</li>
 *   <li>{@code Canvas} – Singleton, das als Zeichenfläche fungiert und alle Shapes rendert</li>
 * </ul>
 *
 * Alle Shapes greifen intern auf {@code Canvas} zu, um über {@code draw()} und {@code erase()}
 * in der Szene dargestellt zu werden.
 */
package main.java.house.shapes;