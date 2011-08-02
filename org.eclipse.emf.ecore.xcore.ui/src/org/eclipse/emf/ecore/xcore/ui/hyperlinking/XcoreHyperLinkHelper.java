package org.eclipse.emf.ecore.xcore.ui.hyperlinking;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.jface.text.Region;
import org.eclipse.xtext.common.types.xtext.ui.TypeAwareHyperlinkHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;

public class XcoreHyperLinkHelper extends TypeAwareHyperlinkHelper
{
  @Override
  public void createHyperlinksTo(XtextResource from, Region region, EObject to, IHyperlinkAcceptor acceptor)
  {
    if (to instanceof EModelElement)
    {
      super.createHyperlinksTo(from, region, XcoreEcoreBuilder.get(to), acceptor);
    }
    else
    {
      super.createHyperlinksTo(from, region, to, acceptor);
    }
  }

}
