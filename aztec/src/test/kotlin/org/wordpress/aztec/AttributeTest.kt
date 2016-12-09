package org.wordpress.aztec

import android.app.Activity
import android.widget.ToggleButton
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import org.wordpress.aztec.source.SourceViewEditText
import org.wordpress.aztec.toolbar.AztecToolbar

/**
 * Testing attribute preservation for supported HTML elements
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
class AttributeTest {

    companion object {
        private val HEADING =
                "<h1 a=\"A\">Heading 1</h1><br>" +
                "<h2 b=\"B\">Heading 2</h2><br>" +
                "<h3 c=\"C\">Heading 3</h3><br>" +
                "<h4 d=\"D\">Heading 4</h4><br>" +
                "<h5 e=\"E\">Heading 5</h5><br>" +
                "<h6 f=\"F\">Heading 6</h6>"
        private val BOLD = "<b h=\"H\">Bold</b>"
        private val BOLD_NO_ATTRS = "<b>Bold</b>"
        private val ITALIC = "<i i=\"I\">Italic</i>"
        private val UNDERLINE = "<u j=\"J\">Underline</u>"
        private val NESTED = "<i a=\"A\"><b><u class=\"klass\">Nested</u></b><i>"
        private val NESTED_REVERSED = "<u class=\"klass\"><b><i a=\"A\">Nested</i></b></u>"
        private val STRIKETHROUGH = "<s class=\"test\">Strikethrough</s>" // <s> or <strike> or <del>
        private val ORDERED = "<ol l=\"L\"><li>Ordered</li></ol>"
        private val UNORDERED = "<ul m=\"M\"><li>Unordered</li></ul>"
        private val QUOTE = "<blockquote n=\"N\">Quote</blockquote>"
        private val LINK = "<a o=\"O\" href=\"https://github.com/wordpress-mobile/WordPress-Aztec-Android\">Link</a>"
        private val UNKNOWN = "<iframe class=\"classic\" p=\"P\">Menu</iframe>"
        private val COMMENT = "<!--Comment--><br>"
        private val COMMENT_MORE = "<!--more--><br>"
        private val COMMENT_PAGE = "<!--nextpage--><br>"
        private val LIST = "<ol><li a=\"1\">Ordered</li></ol>"
        private val LIST_WITH_ATTRIBUTES = "<ul><li a=\"A\"></li><li></li><li b=\"B\">b</li><li c=\"C\">c</li></ul>"
        private val LIST_WITH_EMPTY_ITEMS = "a<ul><li></li><li></li><li a=\"1\">1</li><li></li><li></li></ul>b"

        // TODO: broken due to issue #139 (https://github.com/wordpress-mobile/WordPress-Aztec-Android/issues/139)
//        private val LIST_WITH_EMPTY_ITEMS_WITH_LINE_BREAK = "a<br><ul><li></li><li a=\"1\">1</li><li></li></ul><br>b"

        private val SUB = "<sub i=\"I\">Sub</sub>"
        private val SUP = "<sup i=\"I\">Sup</sup>"
        private val FONT = "<font i=\"I\">Font</font>"
        private val TT = "<tt t=\"T\">Monospace</tt>"
        private val BIG = "<big b=\"B\">Big</big>"
        private val SMALL = "<small s=\"S\">Small</small>"
        private val P = "<p p=\"P\">Paragraph</p>"
        private val MIXED = HEADING + BOLD + ITALIC + UNDERLINE + STRIKETHROUGH + ORDERED +
                UNORDERED + QUOTE + LINK + COMMENT + COMMENT_MORE + COMMENT_PAGE +
                UNKNOWN + LIST + SUB + SUP + FONT + TT + BIG + SMALL + P
    }

    lateinit var editText: AztecText

    /**
     * Initialize variables.
     */
    @Before
    fun init() {
        val activity = Robolectric.buildActivity(Activity::class.java).create().visible().get()
        editText = AztecText(activity)
        activity.setContentView(editText)
    }

    @Test
    @Throws(Exception::class)
    fun headingAttributes() {
        val input = HEADING
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun italicAttributes() {
        val input = ITALIC
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun boldAttributes() {
        val input = BOLD
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun boldWithoutAttributes() {
        val input = BOLD_NO_ATTRS
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun nestedAttributes() {
        val input = NESTED
        val expected = NESTED_REVERSED
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(expected, output)
    }

    @Test
    @Throws(Exception::class)
    fun underlineAttributes() {
        val input = UNDERLINE
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun strikethroughAttributes() {
        val input = STRIKETHROUGH
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun orderedAttributes() {
        val input = ORDERED
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }
    @Test
    @Throws(Exception::class)
    fun unorderedAttributes() {
        val input = UNORDERED
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun quoteAttributes() {
        val input = QUOTE
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun linkAttributes() {
        val input = LINK
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun unknownAttributes() {
        val input = UNKNOWN
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun listAttributes() {
        val input = LIST
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun subAttributes() {
        val input = SUB
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun supAttributes() {
        val input = SUP
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun fontAttributes() {
        val input = FONT
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun typefaceAttributes() {
        val input = TT
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun bigAttributes() {
        val input = BIG
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun smallAttributes() {
        val input = SMALL
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun paragraphAttributes() {
        val input = P
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

    @Test
    @Throws(Exception::class)
    fun mixedAttributes() {
        val input = MIXED + NESTED
        val expected = MIXED + NESTED_REVERSED
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(expected, output)
    }

    @Test
    @Throws(Exception::class)
    fun listWithEmptyItemsAttributes() {
        val input = LIST_WITH_EMPTY_ITEMS
        editText.fromHtml(input)
        val output = editText.toHtml()
        Assert.assertEquals(input, output)
    }

//    @Test
//    @Throws(Exception::class)
//    fun listWithEmptyItemsAndLineBreakAfterItAttributes() {
//        val input = LIST_WITH_EMPTY_ITEMS_WITH_LINE_BREAK
//        editText.fromHtml(input)
//        val output = editText.toHtml()
//        Assert.assertEquals(input, output)
//    }

    @Test
    @Throws(Exception::class)
    fun appendItemToList() {
        val input = LIST
        val originalItem = "<li a=\"1\">Ordered</li>"
        editText.fromHtml(input)
        editText.append("\n")
        editText.append("after")
        Assert.assertEquals("<ol>$originalItem<li>after</li></ol>", editText.toHtml())
        editText.text.insert(0, "\n")
        Assert.assertEquals("<ol><li></li>$originalItem<li>after</li></ol>", editText.toHtml())
        editText.append("\n")
        Assert.assertEquals("<ol><li></li>$originalItem<li>after</li><li></li></ol>", editText.toHtml())
    }

    @Test
    @Throws(Exception::class)
    fun prependItemToList() {
        val input = LIST
        val originalItem = "<li a=\"1\">Ordered</li>"
        editText.fromHtml(input)
        editText.setSelection(0)
        editText.text.insert(0, "before\n")
        Assert.assertEquals("<ol><li>before</li>$originalItem</ol>", editText.toHtml())
        editText.text.insert(0, "\n")
        Assert.assertEquals("<ol><li></li><li>before</li>$originalItem</ol>", editText.toHtml())
        editText.text.delete(editText.length() - 1, editText.length())
        editText.append("\n")
        Assert.assertEquals("<ol><li></li><li>before</li>$originalItem<li></li></ol>", editText.toHtml())
        editText.text.delete(0, 1)
        Assert.assertEquals("<ol><li>before</li>$originalItem<li></li></ol>", editText.toHtml())
    }

    @Test
    @Throws(Exception::class)
    fun moveItemsAround() {
        val input = LIST_WITH_ATTRIBUTES
        editText.fromHtml(input)
        editText.text.insert(0, "\n")
        Assert.assertEquals("<ul><li a=\"A\"></li><li></li><li b=\"B\">b</li><li c=\"C\">c</li></ul>", editText.toHtml())
        editText.text.insert(0, "a")
        editText.text.insert(1, "\n")
        Assert.assertEquals("<ul><li a=\"A\">a</li><li></li><li></li><li b=\"B\">b</li><li c=\"C\">c</li></ul>", editText.toHtml())
        editText.text.insert(0, "\n")
        Assert.assertEquals("<ul><li></li><li a=\"A\">a</li><li></li><li></li><li b=\"B\">b</li><li c=\"C\">c</li></ul>", editText.toHtml())
        editText.text.append("\n")
        Assert.assertEquals("<ul><li></li><li a=\"A\">a</li><li></li><li></li><li b=\"B\">b</li><li c=\"C\">c</li><li></li></ul>", editText.toHtml())
        editText.text.append("\n")
        Assert.assertEquals("<ul><li></li><li a=\"A\">a</li><li></li><li></li><li b=\"B\">b</li><li c=\"C\">c</li></ul>", editText.toHtml())
        editText.text.insert(7, "\n")
        Assert.assertEquals("<ul><li></li><li a=\"A\">a</li><li></li><li></li><li b=\"B\">b</li><li></li><li c=\"C\">c</li></ul>", editText.toHtml())
    }
}