package org.sakaiproject.gradebookng.tool.panels;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import org.sakaiproject.gradebookng.tool.pages.QuickEntryPage;
import org.sakaiproject.portal.util.PortalUtils;

public class BulkGradePanel extends Panel {
    private static final long serialVersionUID = 1L;

    public BulkGradePanel(final String id, boolean comment, double points) {
        super(id);
        AjaxLink<Void> replace = new AjaxLink<Void>("replace"){
            @Override
            public void onClick(AjaxRequestTarget target){
                ((QuickEntryPage) target.getPage()).getBulkGrade().close(target);
            }
        };
        WebMarkupContainer score = new WebMarkupContainer("replacementScore");
        score.add(new AttributeAppender("id","replacementScore"));
        WebMarkupContainer commentBox = new WebMarkupContainer("replacementComment");
        commentBox.add(new AttributeAppender("id","replacementComment"));
        Label pointsLabel = new Label("pointsLabel", " / " + points);
        if(comment){
            replace.add(new AttributeModifier("onclick","fillComments();"));
            add(new Label("replacelabel",getString("quickentry.replacecomment")));
            add(new Label("bulkcaption",getString("quickentry.commentcaption")));
            score.setVisible(false);
            pointsLabel.setVisible(false);
        } else {
            replace.add(new AttributeModifier("onclick","replaceEmptyScores();"));
            add(new Label("replacelabel",getString("quickentry.replacescore")));
            add(new Label("bulkcaption",getString("quickentry.scorecaption")));
            commentBox.setVisible(false);
        }
        add(pointsLabel);
        add(score);
        add(commentBox);
        add(replace);
        AjaxLink<Void> cancel = new AjaxLink<Void>("cancel"){
            @Override
            public void onClick(AjaxRequestTarget target){
                ((QuickEntryPage) target.getPage()).getBulkGrade().close(target);
            }
        };
        add(cancel);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forUrl(String.format("/gradebookng-tool/styles/gradebook-shared.css%s", PortalUtils.getCDNQuery())));
    }
}