package org.diorite.impl.scheduler;

import java.lang.ref.WeakReference;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.Diorite;
import org.diorite.plugin.Plugin;
import org.diorite.scheduler.DioriteTask;
import org.diorite.scheduler.Synchronizable;

public class DioriteTaskImpl implements DioriteTask, Runnable
{
    protected static final int STATE_SINGLE      = - 1;
    protected static final int STATE_CANCEL      = - 2;
    protected static final int STATE_FUTURE      = - 3;
    protected static final int STATE_FUTURE_DONE = - 4;

    private volatile DioriteTaskImpl next = null;
    private volatile long                          period;
    private          long                          nextRun;
    private final    String                        name;
    private final    Runnable                      task;
    private final    Plugin                        plugin;
    private final    boolean                       safeMode;
    private final    WeakReference<Synchronizable> synchronizable;
    private final    int                           id;

    DioriteTaskImpl(final String name)
    {
        this(name, null, null, null, false, - 1, STATE_SINGLE);
    }

    DioriteTaskImpl(final String name, final Runnable task)
    {
        this(name, null, task, null, false, - 1, STATE_SINGLE);
    }

    DioriteTaskImpl(final Runnable task)
    {
        this(task.getClass().getName() + "@" + System.identityHashCode(task), null, task, null, false, - 1, STATE_SINGLE);
    }

    DioriteTaskImpl(final String name, final Plugin plugin, final Runnable task, final Synchronizable synchronizable, final boolean safeMode, final int id, final long period)
    {
        this.name = name;
        this.plugin = plugin;
        this.task = task;
        this.safeMode = safeMode;
        this.synchronizable = new WeakReference<>(synchronizable);
        this.id = id;
        this.period = period;
    }

    @Override
    public final int getTaskId()
    {
        return this.id;
    }

    @Override
    public final Plugin getOwner()
    {
        return this.plugin;
    }

    @Override
    public boolean isAsync()
    {
        return false;
    }

    @Override
    public boolean isSynchronizedTo(final Synchronizable obj)
    {
        return ! this.isAsync() && obj.equals(this.checkReference());
    }

    @Override
    public boolean isSynchronizedTo(final Class<? extends Synchronizable> clazz)
    {
        if (this.isAsync())
        {
            return false;
        }
        final Synchronizable sync = this.checkReference();
        return (sync != null) && clazz.isInstance(sync);
    }

    @Override
    public Synchronizable getSynchronizable()
    {
        return this.checkReference();
    }

    private Synchronizable checkReference()
    {
        final Synchronizable sync = this.synchronizable.get();
        if (this.safeMode)
        {
            if (sync == null)
            {
                this.cancel();
                return null;
            }
            if (! sync.isValidSynchronizable())
            {
                this.synchronizable.clear();
                this.cancel();
                return null;
            }
        }
        return sync;
    }

    @Override
    public void run()
    {
        this.task.run();
    }

    @Override
    public String getTaskName()
    {
        return this.name;
    }

    public long getPeriod()
    {
        return this.period;
    }

    void setPeriod(final long period)
    {
        this.period = period;
    }

    public long getNextRun()
    {
        return this.nextRun;
    }

    void setNextRun(final long nextRun)
    {
        this.nextRun = nextRun;
    }

    DioriteTaskImpl getNext()
    {
        return this.next;
    }

    void setNext(final DioriteTaskImpl next)
    {
        this.next = next;
    }

    public Runnable getTask()
    {
        return this.task;
    }

    @Override
    public void cancel()
    {
        Diorite.getScheduler().cancelTask(this.id);
    }

    public boolean forceCancel()
    {
        this.period = STATE_CANCEL;
        return true;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("period", this.period).append("nextRun", this.nextRun).append("task", this.task).append("plugin", this.plugin).append("synchronizable", this.synchronizable).append("id", this.id).toString();
    }
}

