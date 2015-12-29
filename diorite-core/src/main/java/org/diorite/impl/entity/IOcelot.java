package org.diorite.impl.entity;

import org.diorite.entity.Ocelot;
import org.diorite.utils.math.geometry.ImmutableEntityBoundingBox;

public interface IOcelot extends IAnimalEntity, Ocelot
{
    ImmutableEntityBoundingBox BASE_SIZE = new ImmutableEntityBoundingBox(0.6F, 0.8F);
}
